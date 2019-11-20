package com.github.bluecatlee.bcm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okhttp3.OkHttpClient.Builder;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class OkHttpUtils {

	public static final String DEFAULT_CONTENT_TYPE = "text/plain";
	public static final MediaType DEFAULT_MEDIA_TYPE = MediaType.parse("text/plain");

	private static final ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("all")
	private static OkHttpClient newHttpClient(final boolean isSSL, File file, final String password) {
		try {
			final Builder builder = new Builder();

			if (isSSL) {
				KeyStore keyStore = KeyStore.getInstance("PKCS12");
				FileInputStream instream = new FileInputStream(file);
				keyStore.load(instream, password.toCharArray());
				instream.close();

				KeyManagerFactory keyManagerFactory = KeyManagerFactory
						.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				keyManagerFactory.init(keyStore, password.toCharArray());

				TrustManagerFactory trustManagerFactory = TrustManagerFactory
						.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				trustManagerFactory.init((KeyStore) null);
				TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
				if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
					throw new IllegalStateException(
							"Unexpected default trust managers:" + Arrays.toString(trustManagers));
				}
				X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(keyManagerFactory.getKeyManagers(), new TrustManager[] { trustManager },
						new SecureRandom());

				return builder.sslSocketFactory(sslContext.getSocketFactory(), trustManager).build();
			} else {
				return builder.build();
			}

		} catch (Exception e) {
			throw new RuntimeException("newHttpClient", e);
		}

	}

	@SuppressWarnings("all")
	private static String request(final String url, final boolean isSSL, File file, final String password,
			byte[] bytes, String contentType) {
		
		OkHttpClient client = newHttpClient(isSSL, file, password);
		Request request = null;
		if (bytes != null) {
			request = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse(contentType), bytes))
					.build();
		} else {
			request = new Request.Builder().url(url).build();
		}
		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new RuntimeException("Request >> Unexpected code" + response);
			}
			return response.body().string();

		} catch (IOException e) {
			throw new RuntimeException("Request >> request" + response);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	@SuppressWarnings("all")
	private static String requestIgnoreTrust(final String url, byte[] bytes, String contentType) {

	   X509TrustManager xtm = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				X509Certificate[] x509Certificates = new X509Certificate[0];
				return x509Certificates;
			}
		};

		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSL");

			sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		OkHttpClient client = new OkHttpClient.Builder()
				//.addInterceptor(interceptor)
				.sslSocketFactory(sslContext.getSocketFactory())
				.hostnameVerifier(DO_NOT_VERIFY)
				.build();

		Request request = null;
		if (bytes != null) {
			request = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse(contentType), bytes))
					.build();
		} else {
			request = new Request.Builder().url(url).build();
		}
		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new RuntimeException("Request >> Unexpected code" + response);
			}
			return response.body().string();

		} catch (IOException e) {
			throw new RuntimeException("Request >> request" + response);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	public static String post(final String url, byte[] bytes) {
		return post(url, false, null, null, bytes, String.class);
	}

	public static <E> E post(final String url, byte[] bytes, Class<E> type) {
		return post(url, false, null, null, bytes, type, DEFAULT_CONTENT_TYPE);
	}
	
	public static <E> E post(final String url, byte[] bytes, Class<E> type, String contentType) {
		return post(url, false, null, null, bytes, type, contentType);
	}

	public static <E> E post(final String url, final boolean isSSL, File file, final String password, byte[] bytes,
							 Class<E> type) {
		return post(url, false, null, null, bytes, type, DEFAULT_CONTENT_TYPE);
	}

	public static <E> E post(final String url, final boolean isSSL, File file, final String password, byte[] bytes,
			Class<E> type, String contentType) {
		final String body = request(url, isSSL, file, password, bytes, contentType);
		if (type == String.class) {
			return (E) body;
		}
		if (body != null) {
			try {
				return mapper.readValue(body, type);
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		return null;
	}

	public static <E> E get(final String url, Class<E> type) {
		final String body = request(url, false, null, null, null, DEFAULT_CONTENT_TYPE);
		if (type == String.class) {
			return (E) body;
		}
		if (body != null) {
			try {
				return mapper.readValue(body, type);
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		return null;
	}
}
