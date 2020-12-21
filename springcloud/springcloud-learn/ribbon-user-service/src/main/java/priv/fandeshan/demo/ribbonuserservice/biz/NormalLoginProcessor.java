package priv.fandeshan.demo.ribbonuserservice.biz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import priv.fandeshan.demo.ribbonuserservice.dto.AuthLoginDto;
import priv.fandeshan.demo.ribbonuserservice.enums.LoginTypeEnum;
import priv.fandeshan.demo.ribbonuserservice.mapper.entitys.TbMember;
import priv.fandeshan.demo.ribbonuserservice.mapper.entitys.TbMemberExample;
import priv.fandeshan.demo.ribbonuserservice.mapper.persistence.TbMemberMapper;
import priv.fandeshan.demo.tools.exception.BizException;
import priv.fandeshan.demo.tools.exception.ValidException;

import java.util.List;

/**
 * 账号密码登录实现
 */

@Service
@Slf4j
public class NormalLoginProcessor extends AbstractLogin{

    @Autowired
    private TbMemberMapper tbMemberMapper;

    @Override
    public int getLoginType() {
        return LoginTypeEnum.NORMAL.getCode();
    }

    @Override
    public void validate(AuthLoginDto authLoginDto) {
        if (StringUtils.isBlank(authLoginDto.getUsername())||StringUtils.isBlank(authLoginDto.getPassword())){
            throw new ValidException("账号或者密码错误");
        }
    }

    @Override
    public TbMember doProcessor(AuthLoginDto authLoginDto) {
        log.info("begin NormalLoginProcessor.doProcessor:"+authLoginDto);
        TbMemberExample tbMemberExample = new TbMemberExample();
        tbMemberExample.createCriteria().andStateEqualTo(1).andUsernameEqualTo(authLoginDto.getUsername());
        List<TbMember> tbMembers = tbMemberMapper.selectByExample(tbMemberExample);
        if (tbMembers == null || tbMembers.size() == 0) {
            throw new BizException("用户名或密码错误");
        }
        if (!DigestUtils.md5DigestAsHex(authLoginDto.getPassword().getBytes()).equals(tbMembers.get(0).getPassword())){
            throw new BizException("用户名或密码错误");
        }
        return tbMembers.get(0);
    }
}
