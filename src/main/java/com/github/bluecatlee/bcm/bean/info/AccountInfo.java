package com.github.bluecatlee.bcm.bean.info;

import com.github.bluecatlee.bcm.annotation.Mfs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInfo {

    // 户名(C60)|账 号(C40)|币种 (C3)| 余额 (N14.2)|可用 余额(N14.2)| 开 户 日 期 (C8)| 账户类 型 (C1)| 开户 行(C60)|错误 信息(C200)| 成 功 标 志

    /**
     * 户名
     */
    @Mfs(order = 0)
    private String accountName;

    /**
     * 账号
     */
    @Mfs(order = 1)
    private String account;

    /**
     * 币种
     */
    @Mfs(order = 2)
    private String currencyType;

    /**
     * 余额
     */
    @Mfs(order = 3)
    private String balance;

    /**
     * 可用余额
     */
    @Mfs(order = 4)
    private String availableBalance;

    /**
     * 开户日期
     */
    @Mfs(order = 5)
    private String openAcTime;

    /**
     * 账户类型 1-基本户；2-一般户；3-专用户；4-临时户
     */
    @Mfs(order = 6)
    private String accountType;

    /**
     * 开户行
     */
    @Mfs(order = 7)
    private String openBankName;

    /**
     * 错误信息
     */
    @Mfs(order = 8)
    private String errorMsg;

    /**
     * 成功标志 0：成功； 其他：失败
     */
    @Mfs(order = 9)
    private String successFlag;
}
