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

package net.sourceforge.atunes.kernel.modules.cdripper.encoders;

import java.io.File;

import net.sourceforge.atunes.kernel.modules.cdripper.ProgressListener;


public interface Encoder {
	
	public void setArtist(String artist);
	public void setAlbum(String album);
	public boolean encode(File originalFile, File encodedFile, String title, int trackNumber);
	public String getExtensionOfEncodedFiles();
	public void setListener(ProgressListener listener);
	public void stop();
	public void setQuality(String quality);

}
