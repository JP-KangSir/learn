/*
 * 软件版权: 杭州瑞可科技有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/9/30  regtech  新增
 * ========    =======  ============================================
 */
package com.learning.myproject.service.impl;

import com.learning.myproject.dao.UserDao;
import com.learning.myproject.entity.User;
import com.learning.myproject.entity.UserDTO;
import com.learning.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能说明:
 * 开发人员: regtech regtech@hzregtech.com <br>
 * 开发时间: 2019/9/30 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserDao userDao;


  @Override
  public User getUserByUsername(String username) {
    return userDao.getUserByUsername(username);
  }

  @Override
  public Integer saveUser(UserDTO userDTO) {
   /* Role role = roleDao.selectById(userDTO.getRoleId() != null);
    Integer designerManagerCount = userDao.getDesignerManagerCount(userDTO.getCompanyId());
    if (designerManagerCount>0 && role!=null  && role.getName().equals(RoleNameEnum.DESIGNER_MANAGER.getName()))throw new RepeatDataException("已存在设计师");
    String salt = Md5Utils.generateSalt();
    userDTO.setSalt(salt);
    userDTO.setPassword(Md5Utils.encryption(userDTO.getPassword(),salt));
    if (!userDTO.getIsDistributor().equals(NOT_IS_DISTRIBUTOR)){
      DistributorOrganization distributorInfo = organizationService.getDistributorOrganization();
      if (distributorInfo!=null){
        userDTO.setGroupId(distributorInfo.getGroupId());
        userDTO.setCompanyId(distributorInfo.getCompanyId());
        userDTO.setDepartmentId(distributorInfo.getDepartmentId());
      }
    }
    userDao.saveUser(userDTO);
    return userDTO.getId();*/
   return null;
  }
}