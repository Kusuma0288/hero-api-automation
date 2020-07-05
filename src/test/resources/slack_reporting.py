import os
import io
import glob
import json
import sys
import jsonpath_rw_ext as jp

# from slackclient import SlackClient
import matplotlib

matplotlib.use('Agg')
import matplotlib.pyplot as plt

user = os.environ['USER']
workspace = '~/Woolworths/woolworths-mobile-api-automation'
channel_name = '#testing_python_slack'

if user == 'jenkins':
    workspace = os.environ['WORKSPACE']
    channel_name = '#shopapp-auto-reports'
elif user == 'vsts':
    workspace = '/home/vsts/work/1/s'
    channel_name = '#shopapp-auto-reports'

path = workspace + '/target/cucumber-reports/advanced-reports/'

total_no_of_scenarios = 0
total_scenarios_failed = 0

for filepath in glob.glob(os.path.join(os.path.expanduser(path), '*.json')):
    # print("The Feature file Path is::"+filepath)
    with open(filepath) as json_file:
        json_data = json.load(json_file)

        individual_scenarios = jp.match1("$.[*].elements", json_data)
        total_no_of_scenarios += len(individual_scenarios)
        total_scenarios_failed += len(jp.match("$.[*].elements[?(steps[*].result.status~'.*failed.*')]", json_data))

# print "\n\nThe Total Number of Scenarios::",total_no_of_scenarios
# print "\n\nFailed Scenarios::",total_scenarios_failed

sizes = [total_no_of_scenarios - total_scenarios_failed, total_scenarios_failed]

labels = ('Passed: ' + str((total_no_of_scenarios - total_scenarios_failed)), 'Failed: ' + str(total_scenarios_failed))
colors = ['#58FB4C', '#F8183A']
explode = (0.1, 0)
plt.pie(sizes, explode=explode, labels=labels, colors=colors, autopct='%1.1f%%', shadow=True, startangle=90)
plt.title("PUBLIC TRADER API - REPORT")
filename = workspace + '/target/cucumber-reports/advanced-reports/test.png'
plt.savefig(os.path.expanduser(filename))

'''
SLACK_TOKEN = "xoxb-103281125557-572388960868-Id8YbIkCLdD66f4sFOcIcNXC"
sc = SlackClient(SLACK_TOKEN)

if total_scenarios_failed > 0:
	sc.api_call(
  		"chat.postMessage",
  		channel=channel_name,
		blocks=[
			{
        			"type": "section",
        			"text": {
            				"type": "mrkdwn",
            				"text": "Overall Scenario Status::FAILED ("+str(total_scenarios_failed)+")"
        			}
    		}])
else:
	sc.api_call(
                "chat.postMessage",
                channel=channel_name,
                text="Overall Feature Status:: Passed ("+str((total_no_of_scenarios-total_scenarios_failed))+")"
        )

with open(os.path.expanduser(filename),'rb') as f:
		sc.api_call("files.upload", filename='overall_scenarios.png', title='Prod Public Trader APIs- Results Breakdown', file=io.BytesIO(f.read()),    channels=channel_name)
'''
