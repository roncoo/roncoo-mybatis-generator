<#include "/macro/base.ftl" />
<div class="bjui-pageHeader" style="background: #FFF;">
	<div style="padding: 0 15px;">
		<h4 style="margin-bottom: 20px;">
			龙果学院-运营后台 <small>龙果团队技术支持</small>
		</h4>
	</div>
</div>
<div class="bjui-pageContent">
	<div style="padding: 0 15px;">
		<h4 style="margin-bottom: 20px;">
			今天订单数： <b>${(orderInfo.totalCount)!'0'}</b> 个
			<a href="${base!''}/admin/order/info/list" data-toggle="navtab" data-id="order-info" data-title="订单列表">【查看】</a>
		</h4>
		<h4 style="margin-bottom: 20px;">
			新增会员数： <b>${(oauth2User.totalCount)!'0'}</b> 个
			<a href="${base!''}/admin/oauth2/user/list" data-toggle="navtab" data-id="oauth2-user" data-title="会员列表">【查看】</a>
		</h4>
	</div>
	<div style="border-bottom: 1px #c3ced5 solid;"></div>
	<div style="margin: 20px 10px;">
		<span>龙果学院首页：</span>
		<a href="http://www.roncoo.com/" target="_blank">http://www.roncoo.com/</a>
	</div>
</div>