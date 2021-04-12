Param([string]$ClusterUrl, [string]$Service, [string]$RunEnv, [string]$BuildNumber, [string] $TestRunTitle, [string] $SlackWebhookUrl, [string] $SourceBranchName, [string] $Profile)

$baseUrl = $ClusterUrl + "/" + $Service + "_" + $RunEnv + "_" + $BuildNumber
echo 'baseURL:'$baseUrl
$totalTagUrl = $Profile.ToUpper()

$testResultDetailsUrl = $baseUrl + "/index.html"
$imageURL = $baseUrl + "/advanced-reports/test.png"
$wolvesURL = $baseUrl + "/pages/tag-scenarios/tag_Wolves.html"
$lionURL = $baseUrl + "/pages/tag-scenarios/tag_LION.html"
$lobsterURL = $baseUrl + "/pages/tag-scenarios/tag_Lobsters.html"
$vegemiteURL = $baseUrl + "/pages/tag-scenarios/tag_VEGEMITE.html"
$nutellaURL = $baseUrl + "/pages/tag-scenarios/tag_NUTELLA.html"
$cloverURL = $baseUrl + "/pages/tag-scenarios/tag_CLOVER.html"
$falconURL = $baseUrl + "/pages/tag-scenarios/tag_Falcon.html"
$manukaURL = $baseUrl + "/pages/tag-scenarios/tag_Manuka.html"

##$totalURL = $baseUrl + "/pages/tag-scenarios/tag_" + $totalTagUrl + ".html"
$totalURL = $baseUrl + "/pages/tag-scenarios/tag_REGRESSION_TRADER.html"

##functions
function calculation {
 param ([string]$value)

 $pattern='<li .*class=\s*"list-group-item"[^>]*>(?:.|\n)+?</li>'
 $pattern11='<i[^>]*>(?:.|\n)+?</i>'
 try
 {
   $webclient = New-Object System.Net.WebClient
   $html = $webclient.DownloadString($value)
   $Regex = [Regex]::Matches($html, $pattern)
   $newVal = $Regex.value
   $pattern11='<i[^>]*>(?:.|\n)+?</i>'
   $Regex1 = [Regex]::Matches($newVal, $pattern11)
   $count = $Regex1.value.split('title="passed"></i>')[1].Trim().Substring(0,2)
 }
 catch{
   #"No feature/scenario exists for the tag provided"
   $count = 0
 }
   return $count
}

$wolvesCount = calculation $wolvesURL
$lionCount = calculation $lionURL
$lobsterCount = calculation $lobsterURL
$vegemiteCount = calculation $vegemiteURL
$cloverCount = calculation $cloverURL
$nutellaCount = calculation $nutellaURL
$falconCount = calculation $falconURL
$manukaCount = calculation $manukaURL
$total_failures = calculation $totalURL

if($SourceBranchName -ne 'merge')
{
    $color = 'good';
    $slackMessage = @{
    attachments = @(
    @{
       title = $TestRunTitle
       title_link = $testResultDetailsUrl
       fields = @(
          @{title = 'Source Branch'; value = $SourceBranchName ; short = 'true'},
          @{title = 'Run Env'; value = $RunEnv; short = 'true'}
          if([int]$total_failures -ne 0){
            $color = 'danger';
            @{title = 'Failures:'; short = 'false'}
          }
          @{title = ' '; short = 'false'}
          if([int]$wolvesCount -ne 0){
            @{title = 'Wolves'; value = $wolvesCount; short = 'true'}
          }
          if([int]$lionCount -ne 0){
            @{title = 'Lion'; value = $lionCount; short = 'true'}
          }
          if([int]$lobsterCount -ne 0){
            @{title = 'Lobsters'; value = $lobsterCount; short = 'true'}
          }
          if([int]$vegemiteCount -ne 0){
                @{title = 'Vegemite'; value = $vegemiteCount; short = 'true'}
          }
          if([int]$nutellaCount -ne 0){
                @{title = 'Nutella'; value = $nutellaCount; short = 'true'}
          }
          if([int]$cloverCount -ne 0){
                @{title = 'Clover'; value = $cloverCount; short = 'true'}
          }
          if([int]$falconCount -ne 0){
                @{title = 'Falcon'; value = $falconCount; short = 'true'}
          }
           if([int]$manukaCount -ne 0){
                @{title = 'Manuka'; value = $manukaCount; short = 'true'}
          }
        )
     color = $color
     image_url = $imageURL
    }
    )
    } | ConvertTo-Json -Depth 5

    Invoke-RestMethod -Uri $SlackWebhookUrl `
        -Method Post `
        -Body $slackMessage `
        -UseBasicParsing `
        -ContentType 'application/json'
}
$result = @{ currentFailures = $total_failures; reportURL = $testResultDetailsUrl; }
return $result