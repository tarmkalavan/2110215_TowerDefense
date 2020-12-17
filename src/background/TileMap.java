package background;

import javafx.scene.image.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class TileMap extends ImageView {

	private final String TILEMAP_64 = "res/map/Dirt-Grass-Path-64bit.png";

	private int[][] map;
	private final int RESOLUTION_WIDTH;
	private final int RESOLUTION_HEIGHT;
	private final int TILE_LENGTH_X;
	private final int TILE_LENGTH_Y;
	private final int OFFSET_X;
	private final int OFFSET_Y;
	private final boolean OFFSET_X_FLAG;
	private final boolean OFFSET_Y_FLAG;

	public TileMap(int mapWidth, int mapHeight) {
		RESOLUTION_WIDTH = mapWidth;
		RESOLUTION_HEIGHT = mapHeight;

		TILE_LENGTH_X = (int) Math.ceil(mapWidth / 64d);
		TILE_LENGTH_Y = (int) Math.ceil(mapHeight / 64d);

		OFFSET_X = TILE_LENGTH_X * 64 - RESOLUTION_WIDTH;
		OFFSET_Y = TILE_LENGTH_Y * 64 - RESOLUTION_HEIGHT;

		if (OFFSET_X == 0) {
			OFFSET_X_FLAG = false;
		} else {
			OFFSET_X_FLAG = true;
		}

		if (OFFSET_Y == 0) {
			OFFSET_Y_FLAG = false;
		} else {
			OFFSET_Y_FLAG = true;
		}

		map = generateMapArray();
		repaint();
	}

	private int[][] generateMapArray() {
		int[][] map = new int[][] { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 3, 0, 0, 0, 7, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 3, 0, 0, 0, 4, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 4, 5, 5, 5, 5, 5, 5 },
				{ 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 4, 5, 5, 5, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
			};
		return map;
	}

	public void repaint() {
		
		Image tileMap = new Image(ClassLoader.getSystemResource("GameMap/Dirt-Grass-Path-64bit.png").toString());
		byte[] buffer = new byte[64 * 64 * 4];
		WritablePixelFormat<ByteBuffer> picFormat = WritablePixelFormat.getByteBgraInstance();
		WritableImage paintedMap = new WritableImage(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
		PixelWriter tileWriter = paintedMap.getPixelWriter();

		for (int x = 0; x < TILE_LENGTH_X; x++) {
			for (int y = 0; y < TILE_LENGTH_Y; y++) {
				switch (map[y][x]) {
				case 0:
					tileMap.getPixelReader().getPixels(0, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 1:
					tileMap.getPixelReader().getPixels(64, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 2:
					tileMap.getPixelReader().getPixels(128, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 3:
					tileMap.getPixelReader().getPixels(192, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 4:
					tileMap.getPixelReader().getPixels(256, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 5:
					tileMap.getPixelReader().getPixels(320, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 6:
					tileMap.getPixelReader().getPixels(384, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 7:
					tileMap.getPixelReader().getPixels(448, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				}
				if (y == TILE_LENGTH_Y - 1 & OFFSET_Y_FLAG) {
					tileWriter.setPixels(x * 64, y * 64, 64, OFFSET_Y, picFormat, buffer, 0, 256);
				} else {
					tileWriter.setPixels(x * 64, y * 64, 64, 64, picFormat, buffer, 0, 256);
				}
			}
		}
		;
		this.setImage(paintedMap);
	}
}
