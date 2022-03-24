package com.jayxu.playground.spring.model;

public enum BuiltTransactionType {
    WITHDRAW((short)1, "WITHDRAW_TX_TYPE"),
    INTERNAL((short)2, "INTERNAL_TX_TYPE"),// 零钱整理, 包括eos 4096 <-> 4096
    TRANSFER((short)3, "TRANSFER_TX_TYPE"),// 包括跨业务转账，如果eos16 <-> 4096
    REGISTER((short)4, "REGISTER_TX_TYPE"),
    FEE_TASK((short)5, "FEE_TASK_TX_TYPE"),
    REFUND_TASK((short)6,"REFUND_TASK_TX_TYPE"),
    MERGE_SMALL_MONEY((short)7,"MERGE_SMALL_MONEY_TX_TYPE"),//碎钞整理
    OMNI_SMALL_TRANSFER((short)8,"OMNI_SMALL_TRANSFER_TX_TYPE"),//omni小额转账
    INIT_TRANSFER((short)9,"INIT_TRANSFER_TX_TYPE"),//初始化转账类型：如，一些币种地址内需要转入一定金额后才能正常使用。
    SET_FEE_ADDRESS_TRANSFER((short)10,"SET_FEE_ADDRESS_TRANSFER_TX_TYPE"),//设置手续费地址，例如，xlm中这是一个地址的交易的手续费哪个地址出
    VOTE_TASK((short)11,"VOTE_TASK_TX_TYPE"),//投票相关
    COIN_BURN_TASK((short)12,"COIN_BURN_TX_TYPE"),//铸销币相关
    CLAIM_GAS((short)13,"claim"),//可以用于validator SignRequest.RelatedTask的taskType
    ASSIGN_PERMISSION((short)16,"ASSIGN_PERMISSION"),// 授权交易
    SPLIT_LARGE_VOUT((short)17,"SPLIT_LARGE_VOUT"),// 拆分大额钞票（在老btc里该值取4），由米总的工具产生
    DELEGATEDW((short)18, "DELEGATEDW/UNDELEGTEDW/REFUND"),
    CONTRACT((short)19, "CONTRACT") // 合约
    ;

    private short code;
    private String description;

    BuiltTransactionType(short code, String description) {
        this.code = code;
        this.description = description;
    }

    public short getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }

    public static BuiltTransactionType codeOf(short code) {
        for (BuiltTransactionType enum1 : values()) {
            if (enum1.getCode() == code) return enum1;
        }
        return null;
    }
    public static BuiltTransactionType descriptionOf(String description) {
        for (BuiltTransactionType enum1 : values()) {
            if (enum1.getDescription().equals(description)) return enum1;
        }
        return null;
    }
    public static BuiltTransactionType ordinalOf(int iordinal) {
        if (iordinal < 0 || iordinal >= values().length)
            return null;
        else
            return values()[iordinal];
    }
}
