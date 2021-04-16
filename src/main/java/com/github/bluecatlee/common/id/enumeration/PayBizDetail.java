package com.github.bluecatlee.common.id.enumeration;


/**
 * 支付模块业务详情枚举
 *   最大支持32
 */
public enum PayBizDetail implements BizDetailEnum{

	/**
	 * 用于生成交行请求发起方序号
	 */
	BCM(0),

	CIB(1),

	CCB(2);

	private int value;

	PayBizDetail(int value) {
		this.value = value;
	}

	public static PayBizDetail valueOf(int value) {
		for (PayBizDetail detail : PayBizDetail.values()) {
			if (detail.value == value) {
				return detail;
			}
		}
		return null;
	}

	@Override
	public int getValue() {
		return value;
	}

}