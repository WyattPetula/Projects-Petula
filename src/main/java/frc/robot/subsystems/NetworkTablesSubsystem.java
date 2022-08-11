package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;

public abstract class NetworkTablesSubsystem {
    private NetworkTable table;

    public NetworkTablesSubsystem(String tableName) {
        NetworkTableInstance instance = NetworkTableInstance.getDefault();
        table = instance.getTable(tableName);
    }

    public NetworkTableEntry getEntry(String key) {
        return table.getEntry(key);
    }
}