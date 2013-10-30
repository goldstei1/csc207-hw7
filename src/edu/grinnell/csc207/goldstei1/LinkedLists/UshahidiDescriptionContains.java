package edu.grinnell.csc207.goldstei1.LinkedLists;

import java.io.PrintWriter;
import java.util.Calendar;

import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;

public class UshahidiDescriptionContains {

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
		DoublyLinkedList<UshahidiIncident> incidentList = new DoublyLinkedList<UshahidiIncident>();
		UshahidiWebClient uwc = new UshahidiWebClient(
				"https://farmersmarket.crowdmap.com/");

		while (uwc.hasMoreIncidents()) {
			incidentList.append(uwc.nextIncident());
		}

		DoublyLinkedList<UshahidiIncident> containsStr = (DoublyLinkedList<UshahidiIncident>) incidentList
				.select(new ContainsPred("farmer's"));
		DoublyLinkedListCursor<UshahidiIncident> cur = (DoublyLinkedListCursor<UshahidiIncident>) containsStr
				.front();

		while (containsStr.hasNext(cur)) {
			printIncident(pen, cur.pos.val);
			containsStr.advance(cur);
		}
		printIncident(pen, cur.pos.val);
	}
}

class ContainsPred implements Predicate<UshahidiIncident> {
	String within;

	public ContainsPred(String str) {
		this.within = str;
	}

	public boolean test(UshahidiIncident inc) {
		return inc.getDescription().contains(this.within);
	}
}
