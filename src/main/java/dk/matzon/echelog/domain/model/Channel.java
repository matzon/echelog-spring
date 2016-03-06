package dk.matzon.echelog.domain.model;

import se.citerus.dddsample.domain.shared.Entity;

public class Channel implements Entity<Channel> {
  
  private String network;
  private String name;
  private String description;
  private String url;
	 
    public Channel() {
    }
  
  

    @Override
    public boolean sameIdentityAs(Channel _channel) {
        return network.equals(_channel.network) && name.equals(_channel.name);
    }
}
