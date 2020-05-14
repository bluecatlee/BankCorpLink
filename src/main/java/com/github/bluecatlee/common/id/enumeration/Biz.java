package com.github.bluecatlee.common.id.enumeration;


/**
 * 业务枚举
 */
public enum Biz {

	// 最大支持32

	/**
	 * 保留
	 */
	UNUSED(0),

	/**
	 * 采购商业务
	 */
	PURCHASER(1),

	/**
	 * 供应商业务
	 */
	SUPPLIER(2),

	/**
	 * 平台业务
	 */
	PLATFORM(3),

	/**
	 * 商品业务
	 */
	GOODS(4),

	/**
	 * 订单业务
	 */
	ORDER(5),

	/**
	 * 案例等信息业务
	 */
	INFO(6),

	/**
	 * 支付对账业务
	 */
	PAY(7);

	private int value;

	Biz(int value) {
		this.value = value;
	}

	public static Biz valueOf(int value) {
		for (Biz biz : Biz.values()) {
			if (biz.value == value) {
				return biz;
			}
		}
		return null;
	}

	public int getValue() {
		return value;
	}

}