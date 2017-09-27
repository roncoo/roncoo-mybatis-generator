<!-- 应用上下文 -->
<#assign base=request.contextPath /> 

<!-- 菜单 -->
<#macro navbar>
<ul id="bjui-hnav-navbar">
	<li class="active">
		<a href="javascript:;" data-toggle="slidebar">
			<i class="fa fa-table"></i> 订单管理
		</a>
		<div class="items hide" data-noinit="true">
			<ul id="bjui-hnav-tree2" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="table">
				<li data-id="2" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">订单管理</li>
				<li data-id="20" data-pid="2" data-url="${base}/admin/order/info/list" data-tabid="order-info" data-faicon="list-alt">订单列表</li>
				<li data-id="21" data-pid="2" data-url="${base}/admin/order/log/list" data-tabid="order-log" data-faicon="list-alt">支付列表</li>
			</ul>
		</div>
	</li>

	<li>
		<a href="javascript:;" data-toggle="slidebar">
			<i class="fa fa-table"></i> 会员管理
		</a>
		<div class="items hide" data-noinit="true">
			<ul id="bjui-hnav-tree3" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="table">
				<li data-id="3" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">会员管理</li>
				<li data-id="31" data-pid="3" data-url="${base}/admin/oauth2/user/list" data-tabid="oauth2-user" data-faicon="list-alt">会员列表</li>
				<li data-id="33" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">信用管理</li>
				<li data-id="332" data-pid="33" data-url="${base}/admin/oauth2/credit/info/list" data-tabid="credit-info" data-faicon="list-alt">信用列表</li>
				<li data-id="333" data-pid="33" data-url="${base}/admin/oauth2/credit/file/list" data-tabid="credit-file" data-faicon="list-alt">资料列表</li>
				<li data-id="34" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">学习课时统计</li>
				<li data-id="341" data-pid="34" data-url="${base}/admin/study/period/list" data-tabid="study-period" data-faicon="list-alt">学习列表</li>
				<li data-id="342" data-pid="34" data-url="${base}/admin/study/period/statistics/list" data-tabid="study-period-statistics" data-faicon="list-alt">学习统计</li>
				<li data-id="35" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">认证管理</li>
				<li data-id="351" data-pid="35" data-url="${base}/admin/oauth2/user/certification/info/list" data-tabid="user-certification-info" data-faicon="list-alt">认证列表</li>
				<li data-id="36" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">用户认证管理</li>
				<li data-id="361" data-pid="36" data-url="${base}/admin/oauth2/verify/authenticationLog/list" data-tabid="study-period" data-faicon="list-alt">鉴权日志</li>
				<li data-id="362" data-pid="36" data-url="${base}/admin/oauth2/verify/livingVerifyLog/list" data-tabid="study-period-statistics" data-faicon="list-alt">活体对比日志</li>
			</ul>
		</div>
	</li>

	<li>
		<a href="javascript:;" data-toggle="slidebar">
			<i class="fa fa-table"></i> 课程管理
		</a>
		<div class="items hide" data-noinit="true">
			<ul id="bjui-hnav-tree4" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="table">
				<li data-id="14" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">课程管理</li>
				<li data-id="140" data-pid="14" data-url="${base}/admin/lecturer/course/info/list" data-tabid="course-list" data-faicon="list-alt">课程列表</li>
				<li data-id="142" data-pid="14" data-url="${base}/admin/lecturer/course/info/combination/list" data-tabid="course-combination" data-faicon="list-alt">设置组合</li>
				<li data-id="141" data-pid="14" data-url="${base}/admin/lecturer/course/info/list?auditStatus=0" data-tabid="course-noAudit" data-faicon="list-alt">待审核课程</li>

				<li data-id="24" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">课程分类</li>
				<li data-id="240" data-pid="24" data-url="${base}/admin/lecturer/course/category/list" data-tabid="course-category-list" data-faicon="list-alt">分类列表</li>
				
				<li data-id="25" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">推荐课程</li>
				<li data-id="250" data-pid="25" data-url="${base}/admin/lecturer/course/recommend/list" data-tabid="course-recommend-list" data-faicon="list-alt">课程列表</li>
			</ul>
		</div>
	</li>

	<li>
		<a href="javascript:;" data-toggle="slidebar">
			<i class="fa fa-table"></i> 讲师管理
		</a>
		<div class="items hide" data-noinit="true">
			<ul id="bjui-hnav-tree5" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="table">
				<li data-id="5" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">讲师管理</li>
				<li data-id="51" data-pid="5" data-url="${base}/admin/lecturer/info/list" data-tabid="lecturer-info" data-faicon="list-alt">讲师列表</li>
				<li data-id="52" data-pid="5" data-url="${base}/admin/lecturer/audit/list?auditStatus=0" data-tabid="lecturer-info" data-faicon="list-alt">待审核讲师</li>
				<li data-id="55" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">提现管理</li>
				<li data-id="554" data-pid="55" data-url="${base}/admin/lecturer/extract/list" data-tabid="lecturer-extract" data-faicon="list-alt">提现记录</li>
			</ul>
		</div>
	</li>

	<li>
		<a href="javascript:;" data-toggle="slidebar">
			<i class="fa fa-table"></i> 首页设置
		</a>
		<div class="items hide" data-noinit="true">
			<ul id="bjui-hnav-tree6" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="table">
				<li data-id="6" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">广告设置</li>
				<li data-id="61" data-pid="6" data-url="${base}/admin/activity/adv/module/list" data-tabid="adv-module" data-faicon="list-alt">广告设置</li>
				<!-- <li data-id="62" data-pid="6" data-url="${base}/admin/activity/adv/type/list" data-tabid="adv-type" data-faicon="list-alt">广告分类</li> -->
				<li data-id="66" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">课程设置</li>
				<li data-id="661" data-pid="66" data-url="${base}/admin/lecturer/zone/category/list?platShow=1" data-tabid="home-zone-category" data-faicon="list-alt">Web端课程设置</li>
				<li data-id="662" data-pid="66" data-url="${base}/admin/lecturer/zone/category/list?platShow=2" data-tabid="home-zone-category" data-faicon="list-alt">App端课程设置</li>
				<li data-id="666" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">龙果头条</li>
				<li data-id="6661" data-pid="666" data-url="${base}/admin/activity/news/info/list" data-tabid="news-info" data-faicon="list-alt">头条设置</li>
			</ul>
		</div>
	</li>

	<li>
		<a href="javascript:;" data-toggle="slidebar">
			<i class="fa fa-table"></i> 活动管理
		</a>
		<div class="items hide" data-noinit="true">
			<ul id="bjui-hnav-tree16" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="table">
				<li data-id="16" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">活动管理</li>
				<li data-id="161" data-pid="16" data-url="${base}/admin/activity/act/info/list" data-tabid="act-info" data-faicon="list-alt">活动列表</li>
				<li data-id="66" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">APP管理</li>
				<li data-id="661" data-pid="66" data-url="${base}/admin/activity/app/list" data-tabid="app-version" data-faicon="list-alt">APP版本列表</li>
			</ul>
		</div>
	</li>

	<li>
		<a href="javascript:;" data-toggle="slidebar">
			<i class="fa fa-table"></i> 站内消息
		</a>
		<div class="items hide" data-noinit="true">
			<ul id="bjui-hnav-tree26" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="table">
				<li data-id="26" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">站内消息</li>
				<li data-id="261" data-pid="26" data-url="${base}/admin/activity/sysMsg/info/list" data-tabid="sysMsg-info-list" data-faicon="list-alt">消息管理</li>
				<li data-id="262" data-pid="26" data-url="${base}/admin/activity/sysMsg/type/list" data-tabid="sysMsg-type-list" data-faicon="list-alt">消息分类</li>
			</ul>
		</div>
	</li>

	<li>
		<a href="javascript:;" data-toggle="slidebar">
			<i class="fa fa-cog"></i> 系统管理
		</a>
		<div class="items hide" data-noinit="true">
			<ul id="bjui-hnav-tree6" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="cog">

				<li data-id="17" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">权限管理</li>
				<li data-id="171" data-pid="17" data-url="${base}/admin/system/user/list" data-tabid="system-user" data-faicon="list-alt">用户列表</li>
				<li data-id="171" data-pid="17" data-url="${base}/admin/system/login/list" data-tabid="system/login" data-faicon="list-alt">登录人数管理</li>
				<!-- <li data-id="172" data-pid="17" data-url="${base}/admin/system/role" data-tabid="system-role" data-faicon="list-alt">角色列表</li>
				<li data-id="173" data-pid="17" data-url="${base}/admin/system/permission" data-tabid="system-permission" data-faicon="list-alt">权限列表</li> -->

				<li data-id="7" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">授权管理</li>
				<li data-id="71" data-pid="7" data-url="${base}/admin/oauth2/client/list" data-tabid="oauth2-client" data-faicon="list-alt">应用列表</li>

				<li data-id="27" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">日志查看</li>
				<li data-id="271" data-pid="27" data-url="${base}/admin/oauth2/log/cookie/list" data-tabid="log-cookie" data-faicon="list-alt">登录环境日志</li>
				<li data-id="272" data-pid="27" data-url="${base}/admin/oauth2/log/error/list" data-tabid="log-error" data-faicon="list-alt">登录错误日志</li>
				<li data-id="273" data-pid="27" data-url="${base}/admin/oauth2/log/login/list" data-tabid="log-login" data-faicon="list-alt">登录详细日志</li>
				<li data-id="274" data-pid="27" data-url="${base}/admin/lecturer/log/list" data-tabid="log-login" data-faicon="list-alt">课程操作日志</li>
				<li data-id="275" data-pid="27" data-url="${base}/admin/lecturer/history/list" data-tabid="proportions-history" data-faicon="list-alt">分成变动日志</li>

				<li data-id="37" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">邮件查看</li>
				<li data-id="371" data-pid="37" data-url="${base}/admin/oauth2/email/list?emailType=REGISTER" data-tabid="oauth2-email" data-faicon="list-alt">注册激活邮件</li>
				<li data-id="371" data-pid="37" data-url="${base}/admin/oauth2/email/list?emailType=RESET" data-tabid="oauth2-email" data-faicon="list-alt">密码找回邮件</li>
				
				<li data-id="47" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">推送信息</li>
				<li data-id="471" data-pid="47" data-url="${base}/admin/activity/userUmessage/history/list" data-tabid="userUmessage-history" data-faicon="list-alt">信息记录</li>
			</ul>
		</div>
	</li>
</ul>
</#macro>
