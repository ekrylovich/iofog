/*******************************************************************************
 * Copyright (c) 2016, 2017 Iotracks, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Saeid Baghbidi
 * Kilton Hopkins
 *  Ashita Nagar
 *******************************************************************************/
package org.eclipse.iofog.process_manager;

import java.util.LinkedList;
import java.util.Queue;

public class TaskManager {
	private Queue<ContainerTask> tasks;
	private static TaskManager instance;
	
	private TaskManager() {
		tasks = new LinkedList<>();
	}
	
	public static TaskManager getInstance() {
		if (instance == null) {
			synchronized (TaskManager.class) {
				if (instance == null)
					instance = new TaskManager();
			}
		}
		return instance;
	}
	
	public void addTask(ContainerTask task) {
		synchronized (TaskManager.class) {
			if (!tasks.contains(task))
				tasks.add(task);
		}
	}
	
	public ContainerTask getTask() {
		synchronized (TaskManager.class) {
			return tasks.peek();
		}
	}
	
	public void removeTask(ContainerTask task) {
		synchronized (TaskManager.class) {
			tasks.remove(task);
		}
	}
}
