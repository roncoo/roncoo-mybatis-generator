<!-- 应用上下文 -->
<#assign base=request.contextPath /> 

<!-- 分页头部 -->
<#macro pageHeadr>
<input type="hidden" name="pageSize" value="${(page.pageSize)!}">
<input type="hidden" name="pageCurrent" value="${(page.pageCurrent)!}">
</#macro>

<!-- 分页脚部 -->
<#macro pageFooter>
<div class="bjui-pageFooter">
	<div class="pages">
		<span>每页&nbsp;</span>
		<div class="selectPagesize">
			<select data-toggle="selectpicker" data-toggle-change="changepagesize">
				<#if page??>
				<option value="20"<#if page.pageSize == 20>selected="selected"</#if>>20</option>
				<option value="50"<#if page.pageSize == 50>selected="selected"</#if>>50</option>
				<option value="100"<#if page.pageSize == 100>selected="selected"</#if>>100</option>
				<option value="200"<#if page.pageSize == 200>selected="selected"</#if>>200</option>
				<option value="500"<#if page.pageSize == 500>selected="selected"</#if>>500</option>
				<option value="1000"<#if page.pageSize == 1000>selected="selected"</#if>>1000</option>
				<#else>
				<option value="0">0</option>
				</#if>
			</select>
		</div>
		<span>&nbsp;条，共 ${(page.totalCount)!'0'}条，当前第${(page.pageCurrent)!'0'}页</span>
	</div>
	<div class="pagination-box" data-toggle="pagination" data-total="${(page.totalCount)!}" data-page-size="${(page.pageSize)!}" data-page-current="${(page.pageCurrent)!}"></div>
</div>
</#macro>
