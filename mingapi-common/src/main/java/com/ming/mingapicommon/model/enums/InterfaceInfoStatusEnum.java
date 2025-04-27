package com.ming.mingapicommon.model.enums;

/**
 * @author ming
 * @description
 * @date 2025/4/17 20:17
 */

public enum InterfaceInfoStatusEnum {

    OFFLINE("关闭", 0),
    ONLINE("上线", 1);

    private final String text;
    private final int value;

    InterfaceInfoStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据状态码获取枚举实例
     *
     * @param value 状态码
     * @return 对应的枚举实例，若未找到则返回 null
     */
    public static InterfaceInfoStatusEnum getEnumByValue(int value) {
        for (InterfaceInfoStatusEnum statusEnum : InterfaceInfoStatusEnum.values()) {
            if (statusEnum.getValue() == value) {
                return statusEnum;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
