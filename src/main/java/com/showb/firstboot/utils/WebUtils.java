package com.showb.firstboot.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.StringTokenizer;

@NoArgsConstructor(access = AccessLevel.NONE)
public class WebUtils {
    public static String getClientIp(HttpServletRequest request) {

        String ip = StringUtils.trimAllWhitespace(request.getHeader("X-Forwarded-For"));
        boolean isUnknown = "unknown".equalsIgnoreCase(ip);

        List<String> headers = List.of(
                "x-real-ip",
                "x-original-forwarded-for",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED",
                "HTTP_X_CLUSTER_CLIENT_IP",
                "HTTP_CLIENT_IP",
                "HTTP_FORWARDED_FOR",
                "HTTP_FORWARDED",
                "HTTP_VIA",
                "REMOTE_ADDR"
        );

        for (String header : headers) {
            ip = getNextValueIfInvalidIp(request, ip, header);
        }

        if (ip == null || ip.isEmpty() || isUnknown) {
            ip = request.getRemoteAddr();
        }

        return new StringTokenizer(ip, ",").nextToken();
    }

    private static String getNextValueIfInvalidIp(HttpServletRequest request, String ip, String nextHeader) {
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            return request.getHeader(nextHeader);
        }

        return ip;
    }
}
