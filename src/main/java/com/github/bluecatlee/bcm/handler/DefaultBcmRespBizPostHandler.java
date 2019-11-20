package com.github.bluecatlee.bcm.handler;

import com.github.bluecatlee.bcm.constant.Constants;
import com.github.bluecatlee.bcm.exception.BcmException;
import com.github.bluecatlee.bcm.logger.BcmLogger;
import com.github.bluecatlee.bcm.response.BcmRespHeader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 响应头信息处理器
 */
@Service(Constants.DEFAULT_HANDLER)
public class DefaultBcmRespBizPostHandler implements BcmPostHandler {

    @Autowired(required = false)
    private BcmLogger bcmLogger;

    private static final String CODE = "0";
    private static final String PARTICULAR_CODE = "0000";

    @Override
    public Object handle(BcmRespHeader header) {
        if (header == null) {
            return null;
        }

        String ansCode = header.getAnsCode();
        String ansInfo = header.getAnsInfo();
        String particularCode = header.getParticularCode();
        String particularInfo = header.getParticularInfo();

        if (StringUtils.isNotBlank(ansInfo)) {
            throw new BcmException("直连机调用银行接口失败: " + ansInfo);
        }

        if (!PARTICULAR_CODE.equals(particularCode)) {
            throw new BcmException("银行受理异常：" + particularInfo);
        }

        // desc: 由外层调用者处理异常信息并记录日志

        return null;
    }

}
