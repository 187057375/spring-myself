package com.ciicgat.springmyself.xml;

import lombok.Data;

import java.io.Serializable;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 18:09 2018/9/25
 */
@Data
public class ScanXmlBean implements Serializable{

    private String packageScan;

    private String interceptorPackage;

    private boolean async;
}
