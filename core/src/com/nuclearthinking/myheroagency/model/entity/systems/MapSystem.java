package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.entity.components.MapComponent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;


/**
 * Created by mkuksin on 01.09.2016.
 */
public class MapSystem extends IteratingSystem {
    private OrthographicCamera camera;
    private @Getter @Setter TiledMap tiledMap = null;
    private @Getter @Setter OrthogonalTiledMapRenderer renderer = null;

    public MapSystem(@NonNull World world) {
        this("map/testMap.tmx", world);
    }

    public MapSystem(@NonNull String mapName, @NonNull World world){
        super(Family.all(MapComponent.class).get());

        tiledMap = Asset.getInstance().get(mapName,TiledMap.class);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        @NonNull val properties = tiledMap.getProperties();
        int levelWidth = properties.get("width", Integer.class);
        int levelHeight = properties.get("height", Integer.class);
        int tiledPixelWidth = properties.get("tilewidth", Integer.class);
        int tiledPixelHeight = properties.get("tileheight", Integer.class);
        MapComponent.setLevelPixelWidth(levelWidth*tiledPixelWidth);
        MapComponent.setLevelPixelHeight(levelHeight*tiledPixelHeight);

        tiledMap.dispose();

        buildShapes(renderer.getMap(), "Collision", MapComponent.getPpt(), world);
    }

    public static Array<Body> buildShapes(@NonNull final Map map, final String layer, float pixels, World world){
        @NonNull val objects = map.getLayers().get(layer).getObjects();
        @NonNull val bodies = new Array<Body>();

        for(val object : objects) {

            if (object instanceof TextureMapObject) {
                continue;
            }

            Shape shape;

            if (object instanceof RectangleMapObject) {
                shape = getRectangle((RectangleMapObject)object, pixels);
            }
            else if (object instanceof PolygonMapObject) {
                shape = getPolygon((PolygonMapObject)object, pixels);
            }
            else if (object instanceof PolylineMapObject) {
                shape = getPolyline((PolylineMapObject)object, pixels);
            }
            else if (object instanceof CircleMapObject) {
                shape = getCircle((CircleMapObject)object, pixels);
            }
            else {
                continue;
            }

            @NonNull val bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bd);
            body.createFixture(shape, 1);

            bodies.add(body);

            shape.dispose();
        }

        return bodies;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderer.setView(camera);
        renderer.render();
    }

    public Batch getBatch(){
        return renderer.getBatch();
    }

    private static PolygonShape getRectangle(@NonNull final RectangleMapObject rectangleObject, final float ppt) {
        @NonNull val rectangle = rectangleObject.getRectangle();
        @NonNull val polygon = new PolygonShape();
        @NonNull val size = new Vector2((rectangle.x + rectangle.width * 0.5f) / ppt,
                (rectangle.y + rectangle.height * 0.5f ) / ppt);
        polygon.setAsBox(rectangle.width * 0.5f / ppt,
                rectangle.height * 0.5f / ppt,
                size,
                0.0f);
        return polygon;
    }

    private static CircleShape getCircle(@NonNull final CircleMapObject circleObject, final float ppt) {
        @NonNull val circle = circleObject.getCircle();
        @NonNull val circleShape = new CircleShape();

        circleShape.setRadius(circle.radius / ppt);
        circleShape.setPosition(new Vector2(circle.x / ppt, circle.y / ppt));
        return circleShape;
    }

    private static PolygonShape getPolygon(@NonNull final PolygonMapObject polygonObject, final float ppt) {
        @NonNull val polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();
        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i] / ppt;
        }

        polygon.set(worldVertices);
        return polygon;
    }

    private static ChainShape getPolyline(@NonNull final PolylineMapObject polylineObject, final float ppt) {
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        @NonNull val worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2] / ppt;
            worldVertices[i].y = vertices[i * 2 + 1] / ppt;
        }

        @NonNull val chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }

}
