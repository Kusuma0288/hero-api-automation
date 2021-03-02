package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.context.ApplicationContext;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.utils.SharedData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.InputStream;

public class GraphqlBaseDefinition {

  public GraphqlHelper graphqlHelper;
  public static SharedData sharedData;
  public ObjectMapper mapper;
  public InputStream iStream;
  public ObjectNode variables;

  public GraphqlBaseDefinition(String queryAsset) {
    this.graphqlHelper = new GraphqlHelper();
    this.sharedData = ApplicationContext.getSharedData();
    this.mapper = new ObjectMapper();
    this.iStream = ProductDetailsDefinition.class.getResourceAsStream("/gqlQueries/iris/" + queryAsset);
    this.variables = new ObjectMapper().createObjectNode();
  }
}
