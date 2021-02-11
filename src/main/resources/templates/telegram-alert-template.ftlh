<strong>
    ${alerts?size} alerts received
</strong>
<#list alerts as alert>
    <b>Labels</b>
    alertname = ${alert.labels.alertName}
    instance = ${alert.labels.instance}
    <#if alert.labels.job??>
    job = ${alert.labels.job}
    </#if>
    severity = ${alert.labels.severity}
    <b>Annotations</b>
    description = ${alert.annotations.description}
    summary = ${alert.annotations.summary}

</#list>
