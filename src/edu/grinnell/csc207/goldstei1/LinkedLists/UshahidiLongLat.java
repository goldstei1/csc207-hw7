package edu.grinnell.csc207.goldstei1.LinkedLists;

import java.io.PrintWriter;
import java.util.Calendar;

import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;

public class UshahidiLongLat {
	
	/**
	 * Writes the incident number, title, description, date, location, and
	 * status of an incident to the location given by the printwriter. It is
	 * written in a nicely formatted block
	 * Taken from hw5 written by Daniel Goldstein, Tiffany Nguyen, Mark Lewis, and 
	 * Earnest Wheeler
	 */
	public static void printIncident(PrintWriter pen, UshahidiIncident incident) {
		pen.println("Incident #: " + incident.getId());
		pen.println("  Title: " + incident.getTitle());
		pen.println("  Description: " + incident.getDescription());
		pen.println("  Date: " + (incident.getDate().get(Calendar.MONTH) + 1)
				+ "/" + incident.getDate().get(Calendar.DATE) + "/"
				+ incident.getDate().get(Calendar.YEAR));
		pen.println("  Location: " + incident.getLocation());
		pen.println("  Status: (" + incident.getMode() + ", "
				+ incident.getActive() + ", " + incident.getVerified() + ")");
	} // printIncident

	public static void main(String[] args) throws Exception {
		PrintWriter pen = new PrintWriter(System.out, true);
		int counter = 0;
		double averageLong = 0;
		double averageLat = 0;
		DoublyLinkedList<UshahidiIncident> incidentList = new DoublyLinkedList<UshahidiIncident>();
		UshahidiWebClient uwc = new UshahidiWebClient(
				"https://farmersmarket.crowdmap.com/");

		// Skip the first incident because it is broken and has no longitude or
		// latitude, so for some reason, they are both 191919 which breaks our
		// averages.
		uwc.nextIncident();

		while (uwc.hasMoreIncidents()) {
			incidentList.append(uwc.nextIncident());
			counter++;
		}

		DoublyLinkedListCursor<UshahidiIncident> cur = (DoublyLinkedListCursor<UshahidiIncident>) incidentList
				.front();
		while (incidentList.hasNext(cur)) {
			averageLong += (cur.pos.val.getLocation().getLongitude() / counter);
			averageLat += (cur.pos.val.getLocation().getLatitude() / counter);
			incidentList.advance(cur);
		}
		averageLong += (cur.pos.val.getLocation().getLongitude() / counter);
		averageLat += (cur.pos.val.getLocation().getLatitude() / counter);

		// This list is our correct list of ushahidiincidents within 20 degrees
		// in either direction of the average longitudes and latitudes
		DoublyLinkedList<UshahidiIncident> withinLongLat = (DoublyLinkedList<UshahidiIncident>) incidentList
				.select(new LongLatPred(averageLong, averageLat));
		DoublyLinkedListCursor<UshahidiIncident> cur2 = (DoublyLinkedListCursor<UshahidiIncident>) withinLongLat
				.front();

		while (withinLongLat.hasNext(cur2)) {
			printIncident(pen, cur2.pos.val);
			withinLongLat.advance(cur2);
		}
		printIncident(pen, cur2.pos.val);
	}

}

class LongLatPred implements Predicate<UshahidiIncident> {
	double lon;
	double lat;

	public LongLatPred(double longitude, double latitude) {
		this.lon = longitude;
		this.lat = latitude;
	}

	public boolean test(UshahidiIncident inc) {
		return ((inc.getLocation().getLongitude() < lon + 20)
				&& (inc.getLocation().getLongitude() > lon - 20)
				&& (inc.getLocation().getLatitude() < lat + 20) && (inc
				.getLocation().getLatitude() > lat - 20));
	}
}
