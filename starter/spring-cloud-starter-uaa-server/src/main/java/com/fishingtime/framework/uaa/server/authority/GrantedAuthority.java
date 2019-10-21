package com.fishingtime.framework.uaa.server.authority;

import com.fishingtime.framework.uaa.bean.Privilege;
import lombok.Getter;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-04-25 16:13
 **/
public class GrantedAuthority implements org.springframework.security.core.GrantedAuthority {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    @Getter
    List<Privilege> privileges;
    private String role;
    public GrantedAuthority(String roleCode, List<Privilege> privileges){
        Assert.hasText(roleCode, "A granted authority textual representation is required");
        this.role=roleCode;
        this.privileges=privileges;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
