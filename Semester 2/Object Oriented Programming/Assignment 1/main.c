#include <stdio.h>
#include <stdbool.h>

int read_option()
{
    int option;
    printf("Choose an option:");
    scanf_s("%d",&option);
    return option;
}

void read_array(int array[100])
{
    int pos=0;
    int nr=0;
    while(nr!=-1)
    {
        scanf_s("%d",&nr);
        array[pos++]=nr;
    }
}

/// Checks whether a number is prime or not
/// \param nr - the number to check
/// \return true if the number is prime, false otherwise
bool is_prime(int nr)
{
    if(nr < 2)
        return false;
    if(nr == 2)
        return true;
    if(nr%2==0)
        return false;
    for(int i=3;i*i<=nr;i+=2)
        if(nr%i == 0)
            return false;
    return true;
}

/// Checks if a number has a twin. Two numbers p and q are twins if they are prime and p-q=2
/// \param nr - the number to check
/// \return true if the numbers are twins, false otherwise
bool is_pair(int nr)
{
    if(is_prime(nr)&& is_prime(nr-2))
        return true;
    return false;
}

/// Prints the first n twin numbers by checking for each odd number if it has a twin. Two numbers p and q are twins if they are prime and p-q=2
/// \param n - the given number of pairs to print
void determine_twin_numbers(int n)
{
    int index=1;
    int nr=5;
    while (index<=n)
    {
        if (is_pair(nr))
        {
            printf("Pair %d: %d and %d\n", index, nr - 2, nr);
            index++;
        }
        nr += 2;
    }
}

/// Finds the longest decreasing contiguous subsequence in an array. While iterating thru the array we memorise an
/// intermediary start and end for the decreasing subsequence that we are on. When that intermediary sequence ends
/// (we get to a number that is larger than the previous one) we check to see if the length of the current subsequence is the largest
/// and if so we memorise this in max_start and max_end.At the end we go from max_start to max_end and we print the subsequence.
/// \param array - the given array in which we will find the sequence
void find_longest_sequence(int array[100])
{
    int start,end,max_start,max_end,i=1;
    start=end=max_start=max_end=0;
    while(array[i]!=-1)
    {
        if(array[i]>array[i-1])
        {
            if(end-start>max_end-max_start)
            {
                max_start=start;
                max_end=end;
            }
            start=end=i;
        }
        else
            end++;
        i++;
    }
    printf("Longest decreasing contiguous subsequence:");
    for(i=max_start;i<=max_end;i++)
        printf("%d ",array[i]);
    printf("\n");
}

/// Generates the first n prime numbers. It prints 2 and then it checks for every odd number if it is prime, and if so it prints it
/// \param n - the given number
void generate_prime_numbers(int n)
{

    printf("Prime 1: 2\n");
    int index =2,nr=3;
    while (index<=n)
    {
        if(is_prime(nr))
        {
            printf("Prime %d: %d\n",index,nr);
            index++;
        }
        nr+=2;
    }
}

void print_menu()
{
    printf("1. Read numbers until -1 is read\n");
    printf("2. Determine the first n pairs of twin numbers. Two numbers p and q are twins if they are both prime and p-q=2\n");
    printf("3. Find the longest decreasing contiguous subsequence in the given vector\n");
    printf("4. Determine the first n prime numbers\n");
    printf("5. Exit\n");
}

int main()
{
    int option;
    bool is_read=false;
    int array[100];
    while (true)
    {
        print_menu();
        option = read_option();
        if (option==1)
        {
            printf("Give numbers until -1 is read:\n");
            read_array(array);
            is_read=true;
            printf("Successfully read numbers\n");
        }
        else if(option==2)
        {
            int n;
            printf("n=");
            scanf_s("%d",&n);
            if(n<=0)
                printf("Please input a valid number\n");
            else
                determine_twin_numbers(n);
        }
        else if (option==3)
        {
            if(!is_read)
                printf("Please input an array before choosing this option!\n");
            else
                find_longest_sequence(array);

        }
        else if(option==4)
        {
            int n;
            printf("n=");
            scanf_s("%d",&n);
            if(n<=0)
                printf("Please input a valid number\n");
            else
                generate_prime_numbers(n);
        }
        else if (option==5)
            return 0;
    }
}