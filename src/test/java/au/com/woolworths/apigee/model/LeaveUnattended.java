package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeaveUnattended {
    private boolean ShowLeaveUnattended;
    private boolean DisableLeaveUnattended;
    private boolean CanLeaveUnattended;
    private String WarningMessage;

    @Override
    public String toString() {
        return "LeaveUnattended{" +
                "ShowLeaveUnattended=" + ShowLeaveUnattended +
                ", DisableLeaveUnattended=" + DisableLeaveUnattended +
                ", CanLeaveUnattended=" + CanLeaveUnattended +
                ", WarningMessage='" + WarningMessage + '\'' +
                '}';
    }

    public boolean isShowLeaveUnattended() {
        return ShowLeaveUnattended;
    }

    public void setShowLeaveUnattended(boolean showLeaveUnattended) {
        ShowLeaveUnattended = showLeaveUnattended;
    }

    public boolean isDisableLeaveUnattended() {
        return DisableLeaveUnattended;
    }

    public void setDisableLeaveUnattended(boolean disableLeaveUnattended) {
        DisableLeaveUnattended = disableLeaveUnattended;
    }

    public boolean isCanLeaveUnattended() {
        return CanLeaveUnattended;
    }

    public void setCanLeaveUnattended(boolean canLeaveUnattended) {
        CanLeaveUnattended = canLeaveUnattended;
    }

    public String getWarningMessage() {
        return WarningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        WarningMessage = warningMessage;
    }
}
