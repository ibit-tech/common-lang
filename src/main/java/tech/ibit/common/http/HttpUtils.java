package tech.ibit.common.http;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import tech.ibit.common.collection.CollectionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Http工具类
 *
 * @author IBIT TECH
 *
 */
@UtilityClass
public class HttpUtils {

    private final String X_REAL_IP = "X-Real-IP";
    private final String X_FORWARDED_FOR = "X-Forwarded-For";
    private final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private final String UNKNOWN = "unknown";
    private final String CONTENT_DISPOSITION = "content-disposition";
    private final String FILENAME = "filename";
    private final String PATH_SEPARATOR = "/";
    private final String HEADER_SEPARATOR = ";";
    private final String DEFAULT_CHARSET = "utf-8";
    private final String EQUAL_SEPARATOR = "=";
    private final String EMPTY_STR = "";
    private final String AND_SEPARATOR = "&";
    private final int NAME_INDEX = 0;
    private final int VALUE_INDEX = 1;
    private final String DOMAIN_REGEX = "\\s*,\\s*";
    private final int NAME_ONLY_LENGTH = 1;
    private final int NAME_VALUE_LENGTH = 2;

    private final String SPECIAL_CHAR_REGEX = "[+ /?%#&=]";
    private final String IGNORE_VALUE = "**";

    /**
     * 获取真实ip（经过nginx后）
     *
     * @param request 请求
     * @return 真实ip
     */
    public String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader(X_REAL_IP);
        if (StringUtils.isBlank(ip) || isIpUnknown(ip)) {
            ip = request.getHeader(X_FORWARDED_FOR);
        }
        if (StringUtils.isBlank(ip) || isIpUnknown(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip) || isIpUnknown(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip) || isIpUnknown(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 是否为未知ip
     *
     * @param ip ip
     * @return 是否为未知ip
     */
    private boolean isIpUnknown(String ip) {
        return UNKNOWN.equalsIgnoreCase(ip);
    }

    /**
     * 获取请求参数
     *
     * @param request 请求
     * @return 参数map
     */
    public Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>(10);
        try {
            Enumeration<String> names = request.getParameterNames();
            if (null != names) {
                while (names.hasMoreElements()) {
                    String name = names.nextElement();
                    params.put(name, request.getParameter(name));
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return params;
    }

    /**
     * 获取请求头部信息
     *
     * @param request 请求
     * @return 头部map
     */
    public Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>(10);

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headers.put(key, value);
        }

        return headers;
    }


    /**
     * 获取返回头部信息
     *
     * @param response 返回
     * @return 头部map
     */
    public Map<String, String> getHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>(10);

        Collection<String> headerNames = response.getHeaderNames();
        if (CollectionUtils.isNotEmpty(headerNames)) {
            headerNames.forEach(headerName
                    -> headers.put(headerName, response.getHeader(headerName)));
        }
        return headers;
    }

    /**
     * 获取参数名称
     *
     * @param request 请求
     * @return 参数名称
     */
    public Set<String> getParamNames(HttpServletRequest request) {
        return getParams(request).keySet();
    }


    /**
     * 从queryString中获取参数
     *
     * @param queryString query string
     * @return 参数map
     */
    public Map<String, String> getParams(String queryString) {
        queryString = StringUtils.trimToNull(queryString);
        Map<String, String> params = new HashMap<>();
        for (String paramKV : StringUtils.split(queryString, AND_SEPARATOR)) {
            String[] ids = paramKV.split(EQUAL_SEPARATOR);
            String name = ids[NAME_INDEX];
            if (NAME_ONLY_LENGTH == ids.length) {
                params.put(name, null);
            } else if (NAME_VALUE_LENGTH == ids.length) {
                String value = ids[VALUE_INDEX];
                if (StringUtils.isBlank(value)) {
                    params.put(name, null);
                } else {
                    try {
                        params.put(name, URLDecoder.decode(ids[1], DEFAULT_CHARSET));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return params;

    }

    /**
     * 从multi-part中获取文件名称
     *
     * @param part 文件part
     * @return 文件名称
     */
    public String getFilename(final Part part) {
        for (String content : part.getHeader(CONTENT_DISPOSITION).split(HEADER_SEPARATOR)) {
            if (content.trim().startsWith(FILENAME)) {
                return content.substring(
                        content.indexOf(EQUAL_SEPARATOR) + 1).trim().replace("\"", EMPTY_STR);
            }
        }
        return null;
    }


    /**
     * 通过名称查找cookie
     *
     * @param request    请求
     * @param cookieName cookie名称
     * @return Cookie
     */
    public Cookie findCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 通过cookie名称查找cookie值
     *
     * @param request     请求
     * @param cookingName cookie名称
     * @return cookie值
     */
    public String findCookieValue(HttpServletRequest request, String cookingName) {
        String cookieValue = request.getParameter(cookingName);
        if (!StringUtils.isBlank(cookieValue)) {
            return cookieValue;
        }
        Cookie cookie = findCookieByName(request, cookingName);
        if (null == cookie || StringUtils.isBlank(cookie.getValue())) {
            return null;
        }
        return cookie.getValue();
    }

    /**
     * 获取cookie路径
     *
     * @param request 请求
     * @return cookie路径
     */
    public String getCookiePath(HttpServletRequest request) {
        return request.getContextPath() + PATH_SEPARATOR;
    }

    /**
     * 构造cookie
     *
     * @param cookieName  cookie名称
     * @param cookieValue cookie值
     * @param isSecure    是否安全连接
     * @param path        cookie路径
     * @param domain      cookie域
     * @param maxAge      cookie过期时间（秒）
     * @return Cookie
     */
    public Cookie createCookie(String cookieName, String cookieValue, boolean isSecure
            , String path, String domain, int maxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setSecure(isSecure);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        if (!StringUtils.isBlank(domain)) {
            cookie.setDomain(domain);
        }
        return cookie;
    }

    /**
     * 增加cookie
     *
     * @param request      请求
     * @param response     返回
     * @param cookieName   cookie名称
     * @param cookieDomain cookie域，多个用","分割
     * @param maxAge       cookie过期时间（秒）
     * @param cookieValue  cookie值
     */
    public void addCookies(HttpServletRequest request, HttpServletResponse response
            , String cookieName, String cookieDomain, int maxAge, String cookieValue) {
        addCookies(request, response, cookieName, cookieDomain, maxAge, cookieValue, null);
    }

    /**
     * 增加cookie
     *
     * @param request      请求
     * @param response     返回
     * @param cookieName   cookie名称
     * @param cookieDomain cookie域，多个用","分割
     * @param maxAge       cookie过期时间（秒）
     * @param cookieValue  cookie值
     * @param cookiePath   cookie路径
     */
    public void addCookies(HttpServletRequest request, HttpServletResponse response, String cookieName
            , String cookieDomain, int maxAge, String cookieValue, String cookiePath) {
        cookiePath = null == cookiePath ? getCookiePath(request) : cookiePath;
        if (StringUtils.isBlank(cookieDomain)) {
            Cookie cookie = createCookie(cookieName, cookieValue, request.isSecure()
                    , cookiePath, null, maxAge);
            response.addCookie(cookie);
        } else {
            for (String domain : cookieDomain.split(DOMAIN_REGEX)) {
                if (!StringUtils.isBlank(domain)) {
                    Cookie cookie = createCookie(cookieName, cookieValue, request.isSecure()
                            , cookiePath, domain, maxAge);
                    response.addCookie(cookie);
                }
            }
        }
    }

    /**
     * 获取query string
     *
     * @param params            参数map
     * @param ignoredParamNames 忽略的参数名称集合
     * @return query string
     */
    public String getQueryString(Map<String, String> params, Collection<String> ignoredParamNames) {
        if (CollectionUtils.isEmpty(params)) {
            return EMPTY_STR;
        }
        StringBuilder queryStr = new StringBuilder();
        for (String key : params.keySet()) {
            if (null != ignoredParamNames && ignoredParamNames.contains(key)) {
                queryStr.append(key).append(EQUAL_SEPARATOR).append(IGNORE_VALUE).append(AND_SEPARATOR);
                continue;
            }
            if (null == params.get(key)) {
                queryStr.append(key).append(EQUAL_SEPARATOR).append(AND_SEPARATOR);
                continue;
            }
            queryStr.append(key)
                    .append(EQUAL_SEPARATOR)
                    .append(encode(params.get(key), SPECIAL_CHAR_REGEX, DEFAULT_CHARSET))
                    .append(AND_SEPARATOR);
        }
        if (queryStr.length() > 0) {
            queryStr.deleteCharAt(queryStr.length() - 1);
        }
        return queryStr.toString();
    }

    /**
     * 编码
     *
     * @param str     待编码子串
     * @param regex   正则
     * @param charset 字符集
     * @return 编码后的子串
     */
    private String encode(String str, String regex, String charset) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        StringBuffer b = new StringBuffer();
        while (m.find()) {
            try {
                m.appendReplacement(b, URLEncoder.encode(m.group(), charset));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        m.appendTail(b);
        return b.toString();
    }
}
