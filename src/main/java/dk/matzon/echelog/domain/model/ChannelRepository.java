package dk.matzon.echelog.domain.model;

import java.util.List;

public interface ChannelRepository {
  public List<Channel> findAll();
  public List<Channel> findByNetwork(String _network);
  public Channel findByNetworkAndName(String _network, String _name);
}
