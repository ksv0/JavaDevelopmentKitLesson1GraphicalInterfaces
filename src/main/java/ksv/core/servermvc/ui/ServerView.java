package ksv.core.servermvc.ui;

import ksv.core.servermvc.services.ServerServices;
import ksv.core.servermvc.services.ServerServicesInterface;

public interface ServerView {
    ServerServicesInterface getServices();
}
