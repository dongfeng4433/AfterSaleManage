package com.joindoo.jdwechat.codeGen;

import java.util.*;
/**
* 主数据 - 企业 - 工单 
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2020/3/28.
*/

public class TDataEnterpriseOrderDtoModel extends com.joindoo.jdwechat.codeGen.beans.TDataEnterpriseOrderDtoModel{
    public List<TDataEnterpriseOrder2GoodsDtoModel> details;

    public List<TDataEnterpriseOrder2GoodsDtoModel> getDetails() {
        return details;
    }

    public void setDetails(List<TDataEnterpriseOrder2GoodsDtoModel> details) {
        this.details = details;
    }
}
