<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/plugins/include_static_head.jsp"%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="images/mldn-logo.png" class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
					<shiro:principal />
				</p>
			</div>
		</div>
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header"><i class="fa fa-slack"></i> MLDN报名预约系统</li>
			<shiro:hasRole name="member">
				<li class="${param.role=='member' ? 'active' : ''} treeview"><a
					href="<%=basePath%>pages/back/index.jsp"> <i
						class="fa fa-group"></i> <span>用户管理</span> <i
						class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<shiro:hasPermission name="member:add">
							<li class="${param.action=='member:add' ? 'active' : ''}"><a
								href="<%=basePath%>admin/member/addPre.action"><i
									class="fa fa-plus-square"></i> 增加用户</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="member:list">
							<li class="${param.action=='member:list' ? 'active' : ''}"><a
								href="<%=basePath%>admin/member/list.action"><i
									class="fa fa-list-alt"></i> 用户列表</a></li>
						</shiro:hasPermission>
					</ul></li>
			</shiro:hasRole>
			<li class="${param.role=='news' ? 'active' : ''} treeview"><a
				href="<%=basePath%>pages/back/index.jsp"> <i
					class="fa fa-bullhorn"></i> <span>公告管理</span> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li class="${param.action=='news:add' ? 'active' : ''}"><a
						href="<%=basePath%>admin/news/addPre.action"><i
							class="fa fa-plus-circle"></i> 发布公告</a></li>
					<li class="${param.action=='news:list' ? 'active' : ''}"><a
						href="<%=basePath%>admin/news/list.action"><i
							class="fa fa-list-ul"></i> 公告列表</a></li>
					<li class="${param.action=='news:unlist' ? 'active' : ''}"><a
						href="<%=basePath%>admin/news/listNone.action"><i
							class="fa fa-archive"></i> 公告草稿箱</a></li>
				</ul></li>
			<li class="${param.role=='bespeak' ? 'active' : ''} treeview"><a
				href="<%=basePath%>pages/index.jsp"> <i
					class="fa  fa-slideshare"></i> <span>预约报名管理</span> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li class="${param.action=='bespeak:all' ? 'active' : ''}"><a
						href="<%=basePath%>admin/bespeak/list.action"><i
							class="fa fa-laptop"></i> 预约报名列表</a></li>
					<li class="${param.action=='bespeak:wait' ? 'active' : ''}"><a
						href="<%=basePath%>admin/bespeak/wait.action"><i
							class="fa fa-exclamation-circle"></i> 新的预约报名</a></li>
					<!-- <li class="${param.action=='bespeak:wait' ? 'active' : ''}"><a
						href="pages/back/bespeak/bespeak_list.jsp?type=wait"><i
							class="fa fa-vine"></i> 待处理预约报名</a></li> -->
					<li class="${param.action=='bespeak:finish' ? 'active' : ''}"><a
						href="<%=basePath%>admin/bespeak/finish.action"><i
							class="fa fa-ticket"></i> 已处理预约报名</a></li>
					<li class="${param.action=='bespeak:invalid' ? 'active' : ''}"><a
						href="<%=basePath%>admin/bespeak/invalid.action"><i
							class="fa fa-trash"></i> 无效预约报名</a></li>
				</ul></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>
