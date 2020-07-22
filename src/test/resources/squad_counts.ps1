Param([string]$ServerName)
echo "in squad count script"
echo $ServerName
$webclient = New-Object System.Net.WebClient
$html = $webclient.DownloadString($wolvesURL)
$Regex = [Regex]::Matches($html, $pattern)
$newVal = $Regex.value
$pattern11='<i[^>]*>(?:.|\n)+?</i>'
$Regex1 = [Regex]::Matches($newVal, $pattern11)
return $Regex1.value.split('title="passed"></i>')[1].Trim().Substring(0,1)