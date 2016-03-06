public class StaticChannelRepository implements ChannelRepository {

  private Map<String, List<Channel>> store;
    
  public StaticChannelRepository() {
  }
    
  public List<Channel> findAll() {
  }

  public List<Channel> findByNetwork(String _network) {
      
  }
  
  public Channel findByNetworkAndName(String _network, String _name) {
      
  }
}