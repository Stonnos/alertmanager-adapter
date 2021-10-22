<strong>
    ${alerts?size} alerts received
</strong>
<#list alerts as alert>
    <b>Labels</b>
    alertname = ${alert.labels.alertName}
    <#if alert.labels.instance??>
    instance = ${alert.labels.instance}
    </#if>
    <#if alert.labels.job??>
    job = ${alert.labels.job}
    </#if>
    <#if alert.labels.severity??>
    severity = ${alert.labels.severity}
    </#if>
    <#if alert.annotations??>
    <b>Annotations</b>
    <#if alert.annotations.description??>
    description = ${alert.annotations.description}
    </#if>
    <#if alert.annotations.summary??>
    summary = ${alert.annotations.summary}
    </#if>
    </#if>

</#list>
