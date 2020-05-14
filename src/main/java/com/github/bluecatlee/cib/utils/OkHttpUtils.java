package com.github.bluecatlee.cib.utils;

import okhttp3.*;
import okhttp3.OkHttpClient.Builder;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;

public class OkHttpUtils {

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

	public static String request(final String url, byte[] bytes, Map<String, String> headers) {

		OkHttpClient client = newHttpClient(false, null, null);
		Request.Builder builder = new Request.Builder();
		if (bytes != null) {
			builder.url(url).post(RequestBody.create(MediaType.parse("application/x-fox"), bytes));
		} else {
			builder.url(url);
		}
		if (headers != null && !headers.isEmpty()) {
			headers.forEach((k, v) -> builder.addHeader(k, v));
		}
		Request request = builder.build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new RuntimeException("Request >> unexpected code, response: " + response);
			}
			return response.body().string();

		} catch (IOException e) {
			throw new RuntimeException("Request >> request error, response: " + response, e);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

}
