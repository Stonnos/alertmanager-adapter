<strong>
    ${alertRequest.alerts?size} alerts received
</strong>
<#list alertRequest.alerts as alert>
    <b>Labels</b>
    alertname = ${alert.labels.alertName}
    instance = ${alert.labels.instance}
    job = ${alert.labels.job}
    severity = ${alert.labels.severity}
    <b>Annotations</b>
    description = ${alert.annotations.description}
    summary = ${alert.annotations.summary}

</#list>
