package org.assignment.westernacher.userapp.mappers;

import org.assignment.westernacher.userapp.entities.User;
import org.assignment.westernacher.userapp.model.UserDto;
import org.mapstruct.Mapper;

public class UserMapper {

    public static User userDtoToUser(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( dto.getId() );
        user.firstName( dto.getFirstName() );
        user.lastName( dto.getLastName() );
        user.email( dto.getEmail() );
        user.birthDate( dto.getBirthDate() );

        return user.build();
    }

    public static UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.email( user.getEmail() );
        userDto.birthDate( user.getBirthDate() );

        return userDto.build();
    }

}
