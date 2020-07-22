Param([string]$ServerName)
echo "in squad count script"
echo $ServerName
$pattern='<li .*class=\s*"list-group-item"[^>]*>(?:.|\n)+?</li>'
$pattern11='<i[^>]*>(?:.|\n)+?</i>'

$webclient = New-Object System.Net.WebClient
$html = $webclient.DownloadString($ServerName)
$Regex = [Regex]::Matches($html, $pattern)
$newVal = $Regex.value
$pattern11='<i[^>]*>(?:.|\n)+?</i>'
$Regex1 = [Regex]::Matches($newVal, $pattern11)
return $Regex1.value.split('title="passed"></i>')[1].Trim().Substring(0,1)