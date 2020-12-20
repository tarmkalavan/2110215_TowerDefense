package background;

import javafx.scene.image.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class TileMap extends ImageView {

	private static int[][] map;
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
		int[][] map = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 0, 0, 0, 5, 1, 1, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 5, 1, 1, 6, 0 },
				{ 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 4, 1 },
				{ 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 4, 1, 1, 1, 3, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 4, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		return map;
	}

	public void repaint() {

		Image tileMap = new Image(ClassLoader.getSystemResource("GameMap/Dirt-Grass-Path-Tower-64bit.png").toString());
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
					tileMap.getPixelReader().getPixels(192, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 3:
					tileMap.getPixelReader().getPixels(384, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 4:
					tileMap.getPixelReader().getPixels(256, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 5:
					tileMap.getPixelReader().getPixels(448, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 6:
					tileMap.getPixelReader().getPixels(128, 0, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 7: // BasicTower
					tileMap.getPixelReader().getPixels(0, 64, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 8: // AcidTower
					tileMap.getPixelReader().getPixels(64, 64, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 9: // ArcaneTower
					tileMap.getPixelReader().getPixels(128, 64, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 10: // ArcticTower
					tileMap.getPixelReader().getPixels(192, 64, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 11: // BombardTower
					tileMap.getPixelReader().getPixels(256, 64, 64, 64, picFormat, buffer, 0, 256);
					break;
				case 12: // SniperTower
					tileMap.getPixelReader().getPixels(320, 64, 64, 64, picFormat, buffer, 0, 256);
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

	public static boolean isNodeOpen(int x, int y) {
		if (map[y][x] != 0) {
			return false;
		}
		return true;
	}

	public void setNewNode(int x, int y, int value) {
		map[y][x] = value;
		repaint();
	}

	public ArrayList<Coordinate> getPath() {
		ArrayList<Coordinate> path = new ArrayList<Coordinate>();
		boolean isXAxis = false;
		int prevY = 0;
		int prevX = 0;

		for (int y = 0; !isXAxis; y++) {
			if (map[y][0] > 0) {
				path.add(new Coordinate(0, y));
				isXAxis = true;
				prevY = y;
			}
		}

		for (int x = 0; isXAxis; x++) {
			if (x == TILE_LENGTH_X) {
				path.add(new Coordinate(x - 1, prevY));
				break;
			}

			if (map[prevY][x] > 2 & map[prevY][x] < 7 & x != prevX) {
				path.add(new Coordinate(x, prevY));
				isXAxis = false;
				prevX = x;
			}
			for (int y = 0; !isXAxis; y++) {
				if (map[y][x] > 2 & map[y][x] < 7 & y != prevY) {
					path.add(new Coordinate(x, y));
					isXAxis = true;
					prevY = y;
				}
			}
		}
		return path;
	}

}