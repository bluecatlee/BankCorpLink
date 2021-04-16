package com.github.bluecatlee.ccb.bean;

/**
 * 建行返回码枚举
 *
 * @Date 2021/2/26 15:49
 */
public enum CCBReturnCodeEnum {

    SUCCESS("000000", "交易成功"),
    ERROR_01("0130Z0100001", "系统忙，通讯超时。"),
    ERROR_02("0130Z0100002", "成员行暂时无法处理或通讯错误"),
    ERROR_03("0130Z0100004", "账户密码不正确"),
    ERROR_04("0130Z0100005", "户名不符"),
    ERROR_05("0130Z1101001", "输入的交易要素不完整，请重新输入"),
    ERROR_06("0130Z1101002", "交易输入要素的格式或输入范围错误。"),
    ERROR_07("0130Z1101003", "您输入的信息不符合格式检查。例如，需要的输入数字的要素被输入了字符或输入超长。"),
    ERROR_08("0130Z1101004", "您所提交的账号尚未申请网上操作功能，不能进入交易。"),
    // ...
    ;

    private String code;
    private String desc;

    CCBReturnCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDesc(String dataTypeCode){
        for(CCBReturnCodeEnum enums : CCBReturnCodeEnum.values()){
            if(enums.code.equals(dataTypeCode)) {
                return enums.getDesc();
            }
        }
        return null;
    }

}
