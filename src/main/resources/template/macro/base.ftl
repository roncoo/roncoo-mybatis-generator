<!-- 应用上下文 -->
<#assign base=request.contextPath /> 

<!-- 分页头部 -->
<#macro pageHeadr>
<input type="hidden" name="pageSize" value="${(page.size)!}">
<input type="hidden" name="pageCurrent" value="${(page.current)!}">
</#macro>

<!-- 分页脚部 -->
<#macro pageFooter>
<div class="bjui-pageFooter">
	<div class="pages">
		<span>每页&nbsp;</span>
		<div class="selectPagesize">
			<select data-toggle="selectpicker" data-toggle-change="changepagesize">
				<#if page??>
				<option value="20"<#if page.size == 20>selected="selected"</#if>>20</option>
				<option value="50"<#if page.size == 50>selected="selected"</#if>>50</option>
				<option value="100"<#if page.size == 100>selected="selected"</#if>>100</option>
				<option value="200"<#if page.size == 200>selected="selected"</#if>>200</option>
				<option value="500"<#if page.size == 500>selected="selected"</#if>>500</option>
				<option value="1000"<#if page.size == 1000>selected="selected"</#if>>1000</option>
				<#else>
				<option value="0">0</option>
				</#if>
			</select>
		</div>
		<span>&nbsp;条，共 ${(page.total)!'0'}条，当前第${(page.current)!'0'}页</span>
	</div>
	<div class="pagination-box" data-toggle="pagination" data-total="${(page.count)!}" data-page-size="${(page.size)!}" data-page-current="${(page.current)!}"></div>
</div>
</#macro>
