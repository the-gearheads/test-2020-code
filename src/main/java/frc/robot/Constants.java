/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {
    public static final int K_DRIVE_LEFT_FRONT_ID = 3;
    public static final int K_DRIVE_LEFT_BACK_ID = 10;
    public static final int K_DRIVE_RIGHT_FRONT_ID = 2;
    public static final int K_DRIVE_RIGHT_BACK_ID = 12;
    public static final boolean K_DRIVE_LEFT_INVERT = false;
    public static final boolean K_DRIVE_RIGHT_INVERT = true;
    public static final double SPEED = 1.5;

    public static final double LDRIVE_P = .1; // .1 rev
    public static final double LDRIVE_D = 0;
    public static final double LDRIVE_A = 0.489;
    public static final double LDRIVE_V = 3.41;
    public static final double LDRIVE_S = 0.13;

    public static final double RDRIVE_P = .2; // .172 rev
    public static final double RDRIVE_D = 0;
    public static final double RDRIVE_A = 0.462;
    public static final double RDRIVE_V = 3.41;
    public static final double RDRIVE_S = 0.138;
}
