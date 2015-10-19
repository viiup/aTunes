/**
 * @author amoyeen
 *
 */
package net.sourceforge.atunes.model.player;

import java.util.Comparator;
import java.lang.Long;

import net.sourceforge.atunes.kernel.modules.repository.audio.AudioFile;


public class PlayListDurationComparator implements Comparator {

	public static PlayListDurationComparator comparator = new PlayListDurationComparator();
	
	public PlayListDurationComparator() {
		super();
	}

	public int compare(Object o1, Object o2) {
		AudioFile f1 = (AudioFile) o1;
		AudioFile f2 = (AudioFile) o2;
		return Long.compare(f1.getDuration(), f2.getDuration());
	}
}
