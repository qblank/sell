<html>
<#include "../common/header.ftl">
<style>
    .table tbody tr td{
        vertical-align: middle;
    }
</style>
<body>
<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed" >
                        <thead>
                        <tr>
                            <th class="text-center">商品编号</th>
                            <th class="text-center">名称</th>
                            <th class="text-center">图片</th>
                            <th class="text-center">单价</th>
                            <th class="text-center">库存</th>
                            <th class="text-center">描述</th>
                            <th class="text-center">类目</th>
                            <th class="text-center">创建时间</th>
                            <th class="text-center">修改时间</th>
                            <th colspan="2" class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productInfoPage.content as productInfo>
                        <tr class="text-center">
                            <td>${productInfo.productId}</td>
                            <td>${productInfo.productName}</td>
                            <td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>
                            <td>${productInfo.productPrice}</td>
                            <td>${productInfo.productStock}</td>
                            <td>${productInfo.productDescription}</td>
                            <td>${productInfo.categoryType}</td>
                            <td>${productInfo.createTime}</td>
                            <td>${productInfo.updateTime}</td>
                            <td><a href="/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                            <td>
                                <#if productInfo.getProductStatusEnum().message == "上架">
                                    <a href="/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                <#else>
                                    <a href="/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
                                </#if>
                                <#--<a href="#">下架</a>-->
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled">
                                <a>上一页</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/seller/product/list?page=${currentPage-1}&size=${size}"> 上一页</a>
                            </li>
                        </#if>

                        <#list   1..productInfoPage.getTotalPages() as index >
                            <#if currentPage==index>
                                <li class="disabled">
                                    <a>${index}</a>
                                </li>
                            <#else >
                                <li>
                                    <a href="/seller/product/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>

                        </#list>

                        <#if currentPage gte productInfoPage.getTotalPages()>
                            <li class="disabled">
                                <a>下一页</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/seller/product/list?page=${currentPage+1}&size=${size}"> 下一页</a>
                            </li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
</html>