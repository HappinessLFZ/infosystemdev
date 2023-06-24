package com.classdesign.infosystemdev.enums;

import java.io.Serializable;

public interface BaseEnum  extends Serializable {

    /**
     * 获得编号
     * @return
     */
    Integer  getCode();

    /**
     *消息内容
     * @return
     */

    String  getMessage();

}
