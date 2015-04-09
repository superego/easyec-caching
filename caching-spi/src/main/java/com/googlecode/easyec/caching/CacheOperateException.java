package com.googlecode.easyec.caching;

/**
 * 缓存异常类。当操作缓存时，发生错误，都抛出此异常。
 * <p>
 * 操作异常情况有：
 * <ol>
 * <li>向缓存中放入值</li>
 * <li>从缓存中读取值</li>
 * <li>从缓存中删除值</li>
 * </ol>
 * </p>
 *
 * @author JunJie
 */
public class CacheOperateException extends RuntimeException {

    private static final long serialVersionUID = 2192727548580070169L;

    public CacheOperateException(String message) {
        super(message);
    }

    public CacheOperateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheOperateException(Throwable cause) {
        super(cause);
    }
}
