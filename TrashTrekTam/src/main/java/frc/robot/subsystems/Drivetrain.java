// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
//quick fix imported I2C.Port??
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends SubsystemBase {
  //Motor objects
    CANSparkMax m_leftMotorSlave1 = new CANSparkMax(1,MotorType.kBrushless);
    CANSparkMax m_leftMotorSlave2 = new CANSparkMax(2, MotorType.kBrushless);
    CANSparkMax m_leftMotorMaster = new CANSparkMax(3, MotorType.kBrushless);
    CANSparkMax m_rightMotorSlave1 = new CANSparkMax(4, MotorType.kBrushless);
    CANSparkMax m_rightMotorSlave2 = new CANSparkMax(5, MotorType.kBrushless);
    CANSparkMax m_rightMotorMaster = new CANSparkMax(6, MotorType.kBrushless);

    MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_leftMotorSlave1,m_leftMotorSlave2,m_leftMotorMaster);
    MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_rightMotorSlave1,m_rightMotorSlave2,m_rightMotorMaster);

    DifferentialDrive m_differentialDrive = new DifferentialDrive(m_leftGroup,m_rightGroup);

    AHRS m_gyroscope = new AHRS(Port.kMXP);
    
    public Drivetrain() {
      m_leftGroup.setInverted(true);
      //m_rightGroup.setInverted(true);
    } 

  public void arcadeDrive(double xaxisSpeed, double zaxisRotate){
    m_differentialDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
  }
  



  @Override
  public void periodic() {
    SmartDashboard.putNumber("GetGyroZAngle",m_gyroscope.getAngle());
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
