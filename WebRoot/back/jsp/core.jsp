<%@ page language="java" pageEncoding="utf-8"%>
var user = eval(${sessionScope.userinfo});
var userinfo = SX.transRoleAndUserGroupInfo(user);