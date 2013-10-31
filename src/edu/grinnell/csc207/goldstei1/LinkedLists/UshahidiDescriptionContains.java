package edu.grinnell.csc207.goldstei1.LinkedLists;

import java.io.PrintWriter;
import java.util.Calendar;

import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;

/**
 * Class that reads a set of UshahidiIncidents into a list and finds all of the
 * incidents that have a certain string in their description. Then it prints all
 * of these incidents.
 * 
 * @author Daniel Goldstein, Mark Lewis, Earnest Wheeler
 * 
 */
public class UshahidiDescriptionContains {

    /**
     * Writes the incident number, title, description, date, location, and
     * status of an incident to the location given by the printwriter. It is
     * written in a nicely formatted block Taken from hw5 written by Daniel
     * Goldstein, Tiffany Nguyen, Mark Lewis, and Earnest Wheeler
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

	// put all the incidents into a doublylinkedlist
	while (uwc.hasMoreIncidents()) {
	    incidentList.append(uwc.nextIncident());
	}

	// check if people use correct grammar (or check for any other string
	// within the description)
	DoublyLinkedList<UshahidiIncident> containsStr = (DoublyLinkedList<UshahidiIncident>) incidentList
		.select(new ContainsPred("farmer's"));
	DoublyLinkedListCursor<UshahidiIncident> cur = (DoublyLinkedListCursor<UshahidiIncident>) containsStr
		.front();

	// Print the incidents with "farmer's" in their description
	while (containsStr.hasNext(cur)) {
	    printIncident(pen, cur.pos.val);
	    containsStr.advance(cur);
	}
	printIncident(pen, cur.pos.val);
    } // main
} // UshahidiDescriptionContains

/**
 * Predicate that tests if a String is contained in the description of an
 * ushahidiIncident
 */
class ContainsPred implements Predicate<UshahidiIncident> {
    String within;

    public ContainsPred(String str) {
	this.within = str;
    } // ContainsPred

    public boolean test(UshahidiIncident inc) {
	return inc.getDescription().contains(this.within);
    } // test
} // ContainsPred
