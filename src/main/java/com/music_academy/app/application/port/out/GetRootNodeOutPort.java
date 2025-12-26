package com.music_academy.app.application.port.out;

import com.music_academy.app.domain.model.Node;
import com.music_academy.app.domain.model.User;

public interface GetRootNodeOutPort {
	Node getRootNode(User user);
}
