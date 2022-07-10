<#-- @ftlvariable name="users" type="kotlin.collections.List<com.alhussein.models.User>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <#list users?reverse as article>
        <div>
            <h3>
                <a href="/articles/${article.id}">${article.name}</a>
            </h3>
            <p>
                ${article.status}
            </p>
        </div>
    </#list>
    <hr>
    <p>
        <a href="/articles/new">Create article</a>
    </p>
</@layout.header>