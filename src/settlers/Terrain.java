package settlers;

public class Terrain {

    private final GameObject[] GameObjects;

    public Terrain(int blocksPerRow, float scale, float minY, float maxY, String heightMap, Texture texture, int textInc) throws Exception {
        GameObjects = new GameObject[blocksPerRow * blocksPerRow];
        HeightMapMesh heightMapMesh = new HeightMapMesh(minY, maxY, heightMap, texture, textInc);
        for (int row = 0; row < blocksPerRow; row++) {
            for (int col = 0; col < blocksPerRow; col++) {
                float xDisplacement = (col - ((float) blocksPerRow - 1) / (float) 2) * scale * HeightMapMesh.getXLength();
                float zDisplacement = (row - ((float) blocksPerRow - 1) / (float) 2) * scale * HeightMapMesh.getZLength();

                GameObject terrainBlock = new GameObject(heightMapMesh.getMesh());
                terrainBlock.setScale(scale);
                terrainBlock.setPosition(xDisplacement, 0, zDisplacement);
                GameObjects[row * blocksPerRow + col] = terrainBlock;
            }
        }
    }

    public GameObject[] getGameObjects() {
        return GameObjects;
    }
}