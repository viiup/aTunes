/**
 * aTunes 1.6.0
 * Copyright (C) 2006-2007 Alex Aranda (fleax) alex.aranda@gmail.com
 *
 * http://www.atunes.org
 * http://sourceforge.net/projects/atunes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package net.sourceforge.atunes.kernel.handlers;

import java.util.Comparator;
import net.sourceforge.atunes.kernel.modules.repository.audio.AudioFile;
import net.sourceforge.atunes.kernel.HandlerProxy;
import net.sourceforge.atunes.model.player.PlayList;
import java.util.Collections;

// amoyeen: extracted from God Class PlayListHandler.java
public class PlayListHandlerOperations {

	public void sortPlayList(Comparator comp, PlayListHandler playListHandler) {
		AudioFile currentFile = HandlerProxy.getPlayerHandler()
				.getCurrentPlayList().getCurrentFile();
		PlayList currentPlaylist = HandlerProxy.getPlayerHandler()
				.getCurrentPlayList();
		Collections.sort(currentPlaylist, comp);
		int pos = currentPlaylist.indexOf(currentFile);
		HandlerProxy.getVisualHandler().getPlayListTableModel().removeSongs();
		playListHandler.setPlayList(currentPlaylist);
		currentPlaylist.setNextFile(pos);
		HandlerProxy.getControllerHandler().getPlayListController()
				.setSelectedSong(pos);
	}

	public void moveToTop(int[] rows) {
		PlayList currentPlayList = HandlerProxy.getPlayerHandler()
				.getCurrentPlayList();
		for (int i = 0; i < rows.length; i++) {
			AudioFile aux = currentPlayList.get(rows[i]);
			currentPlayList.remove(rows[i]);
			currentPlayList.add(i, aux);
		}
		if (rows[0] > currentPlayList.getNextFile()) {
			currentPlayList.setNextFile(currentPlayList.getNextFile()
					+ rows.length);
			HandlerProxy.getControllerHandler().getPlayListController()
					.setSelectedSong(currentPlayList.getNextFile());
		} else if (rows[0] <= currentPlayList.getNextFile()
				&& currentPlayList.getNextFile() <= rows[rows.length - 1]) {
			currentPlayList
					.setNextFile(currentPlayList.getNextFile() - rows[0]);
			HandlerProxy.getControllerHandler().getPlayListController()
					.setSelectedSong(currentPlayList.getNextFile());
		}
	}

	public void moveUp(int[] rows) {
		PlayList currentPlayList = HandlerProxy.getPlayerHandler()
				.getCurrentPlayList();
		for (int i = 0; i < rows.length; i++) {
			AudioFile aux = currentPlayList.get(rows[i]);
			currentPlayList.remove(rows[i]);
			currentPlayList.add(rows[i] - 1, aux);
		}
		if (rows[0] - 1 == currentPlayList.getNextFile()) {
			currentPlayList.setNextFile(currentPlayList.getNextFile()
					+ rows.length);
			HandlerProxy.getControllerHandler().getPlayListController()
					.setSelectedSong(currentPlayList.getNextFile());
		} else if (rows[0] <= currentPlayList.getNextFile()
				&& currentPlayList.getNextFile() <= rows[rows.length - 1]) {
			currentPlayList.setNextFile(currentPlayList.getNextFile() - 1);
			HandlerProxy.getControllerHandler().getPlayListController()
					.setSelectedSong(currentPlayList.getNextFile());
		}
	}

	public void moveDown(int[] rows) {
		PlayList currentPlayList = HandlerProxy.getPlayerHandler()
				.getCurrentPlayList();
		for (int i = rows.length - 1; i >= 0; i--) {
			AudioFile aux = currentPlayList.get(rows[i]);
			currentPlayList.remove(rows[i]);
			currentPlayList.add(rows[i] + 1, aux);
		}
		if (rows[rows.length - 1] + 1 == currentPlayList.getNextFile()) {
			currentPlayList.setNextFile(currentPlayList.getNextFile()
					- rows.length);
			HandlerProxy.getControllerHandler().getPlayListController()
					.setSelectedSong(currentPlayList.getNextFile());
		} else if (rows[0] <= currentPlayList.getNextFile()
				&& currentPlayList.getNextFile() <= rows[rows.length - 1]) {
			currentPlayList.setNextFile(currentPlayList.getNextFile() + 1);
			HandlerProxy.getControllerHandler().getPlayListController()
					.setSelectedSong(currentPlayList.getNextFile());
		}
	}

	public void moveToBottom(int[] rows) {
		PlayList currentPlayList = HandlerProxy.getPlayerHandler()
				.getCurrentPlayList();
		int j = 0;
		for (int i = rows.length - 1; i >= 0; i--) {
			AudioFile aux = currentPlayList.get(rows[i]);
			currentPlayList.remove(rows[i]);
			currentPlayList.add(currentPlayList.size() - j++, aux);
		}
		if (rows[rows.length - 1] < currentPlayList.getNextFile()) {
			currentPlayList.setNextFile(currentPlayList.getNextFile()
					- rows.length);
			HandlerProxy.getControllerHandler().getPlayListController()
					.setSelectedSong(currentPlayList.getNextFile());
		} else if (rows[0] <= currentPlayList.getNextFile()
				&& currentPlayList.getNextFile() <= rows[rows.length - 1]) {
			currentPlayList.setNextFile(currentPlayList.getNextFile()
					+ currentPlayList.size() - rows[rows.length - 1] - 1);
			HandlerProxy.getControllerHandler().getPlayListController()
					.setSelectedSong(currentPlayList.getNextFile());
		}
	}
}
