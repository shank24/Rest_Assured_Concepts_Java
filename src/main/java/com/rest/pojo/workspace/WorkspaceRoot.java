package com.rest.pojo.workspace;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkspaceRoot {
    Workspace workspace;

    public WorkspaceRoot(Workspace workspace) {
        this.workspace = workspace;
    }

    public WorkspaceRoot() {

    }
}
